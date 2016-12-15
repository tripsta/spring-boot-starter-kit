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

  private FluentLogger fluentLogger;
  private ExecutorService service;
  private String tag;
  private String label;
  private String remoteHost;
  private int port;
  private int maxThreads;

  public FluentAppender() {
  }

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
      System.out.println("Host: " + remoteHost + ", Port: " + port);
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
    Map<String, Object> messages = new HashMap<String, Object>();

    messages.put("host", getHostname());
    messages.put("app", label);
    messages.put("main", System.getProperty("sun.java.command"));
    messages.put("thread", event.getThreadName());
    messages.put("level", event.getLevel().toString());
    messages.put("class", event.getLoggerName());
    if (event.getThrowableProxy() == null) { // Without exception
      messages.put("exception", "--- This.log.is.not.an.exception ---");
      Throwable tempThrowable = new Throwable();
      tempThrowable.setStackTrace(event.getCallerData());
      messages.put("message", event.getFormattedMessage().toString());
      if (event.getCallerData() != null) {
        messages.put("stacktrace", extractCallerData(tempThrowable));
      }
    } else { // With exception
      ThrowableProxy throwableProxy = (ThrowableProxy) event.getThrowableProxy();
      messages.put("exception", throwableProxy.getClassName());
      messages.put("message", throwableProxy.getMessage());
      messages.put("stacktrace", extractCallerData(throwableProxy.getThrowable()));
    }
    Long timeStamp = event.getTimeStamp() / 1000;
    service.submit(() -> {
      fluentLogger.log(label, messages, timeStamp);
    });
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
