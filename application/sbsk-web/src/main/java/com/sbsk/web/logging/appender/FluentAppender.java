package com.sbsk.web.logging.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.AppenderBase;
import org.fluentd.logger.FluentLogger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FluentAppender extends AppenderBase<ILoggingEvent> {

  private static final String HOST_KEY = "host";
  private static final String APP_KEY = "app";
  private static final String ENV_KEY = "environment";
  private static final String MAIN_KEY = "main";
  private static final String THREAD_KEY = "thread";
  private static final String LEVEL_KEY = "level";
  private static final String CLASS_KEY = "class";
  private static final String TYPE_KEY = "type";
  private static final String EXCEPTIONS_KEY = "exceptions";
  private static final String MESSAGE_KEY = "message";
  private static final String STACKTRACE_KEY = "stacktrace";
  private static final String TYPE_EXCEPTION = "EXCEPTION";
  private static final String TYPE_MESSAGE = "MESSAGE";

  private FluentLogger fluentLogger;
  private ExecutorService service;
  private String tag;
  private String label;
  private String environment;
  private String remoteHost;
  private int port;
  private int maxThreads;

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getEnvironment() {
    return environment;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }

  public String getRemoteHost() {
    return remoteHost;
  }

  public void setRemoteHost(String remoteHost) {
    this.remoteHost = remoteHost;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public int getMaxThreads() {
    return maxThreads;
  }

  public void setMaxThreads(int maxThreads) {
    this.maxThreads = maxThreads;
  }

  @Override
  public void start() {
    try {
      fluentLogger = FluentLogger.getLogger(tag, remoteHost, port);
      this.service = Executors.newFixedThreadPool(maxThreads);
    } catch (RuntimeException e) {
      addError("Cannot create FluentLogger.", e);
    }
    super.start();
  }

  @Override
  public void stop() {
    try {
      fluentLogger.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
    super.stop();
  }

  @Override
  protected void append(ILoggingEvent event) {
    Map<String, Object> messages = new HashMap<>();

    addBaseData(event, messages);
    if (eventHasExceptions(event)) {
      addExceptionData(event, messages);
    } else {
      addMessageData(event, messages);
    }

    Long timeStamp = event.getTimeStamp() / 1000;
    service.submit(() -> fluentLogger.log(label, messages, timeStamp));
  }

  private void addBaseData(ILoggingEvent event, Map<String, Object> messages) {
    messages.put(HOST_KEY, getHostname());
    messages.put(APP_KEY, label);
    messages.put(ENV_KEY, environment);
    messages.put(MAIN_KEY, System.getProperty("sun.java.command"));
    messages.put(THREAD_KEY, event.getThreadName());
    messages.put(LEVEL_KEY, event.getLevel().toString());
    messages.put(CLASS_KEY, event.getLoggerName());
  }

  private boolean eventHasExceptions(ILoggingEvent event) {
    return event.getThrowableProxy() != null;
  }

  private void addExceptionData(ILoggingEvent event, Map<String, Object> messages) {
    ThrowableProxy throwableProxy = (ThrowableProxy) event.getThrowableProxy();
    messages.put(EXCEPTIONS_KEY, throwableProxy.getClassName());
    messages.put(TYPE_KEY, TYPE_EXCEPTION);
    messages.put(MESSAGE_KEY, throwableProxy.getMessage());
    messages.put(STACKTRACE_KEY, extractCallerData(throwableProxy.getThrowable()));
  }

  private void addMessageData(ILoggingEvent event, Map<String, Object> messages) {
    messages.put(TYPE_KEY, TYPE_MESSAGE);
    if (eventContainsDataInMap(event)) {
      addMapData(event.getArgumentArray()[0], messages);
    } else {
      messages.put(MESSAGE_KEY, event.getMessage());
    }
  }

  private boolean eventContainsDataInMap(ILoggingEvent event) {
    return event.getArgumentArray() != null && event.getArgumentArray()[0] instanceof HashMap;
  }

  private void addMapData(Object obj, Map<String, Object> messages) {
    HashMap<String, String> map = (HashMap<String, String>) obj;
    map.forEach((key, value) -> messages.put(key, value));
  }

  private String getHostname() {
    try {
      return InetAddress.getLocalHost().getCanonicalHostName();
    } catch (Exception e) {
      return "hostname not available";
    }
  }

  private String extractCallerData(Throwable throwable) {
    StringWriter writer = new StringWriter();
    PrintWriter printWriter = new PrintWriter(writer);
    throwable.printStackTrace(printWriter);
    printWriter.flush();
    return writer.toString();
  }
}
