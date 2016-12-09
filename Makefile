# Makefile variables
#FOO?=foo value

# Build java app
build:
	foo

# Remove logs, cached and temp files (This should not reset already resolved dependencies)
clean:
	foo

# Prepare required config files. For example, create them using scripts or copy them from .example files
configs:
	foo

# Download and prepare dependencies (This should not reset already resolved dependencies)
deps:
	foo

# Reset dependencies
deps_clean:
	foo

# Run migrations
migrate:
	foo

# Run migrations in test db
migrate_test_db:
	foo

# Create appropriate folders if missing and change permissions
prepare_folders:
	foo

# Create db if needed and prepare it
prepare_db:
	foo


# Create test db if needed and prepare it
prepare_test_db:
	foo

# Start the main process. This can be a server or any service
run:
	foo

# Start the main process in the foreground. This can be a server or any service
run_foreground:
	foo

# Insert data seeds in db
seeds:
	foo

# Install system dependencies. This is mostly used in linux machines to validate all system dependencies are resolved.
system_deps:
	if [[ $(uname) == "Linux" ]]; then
    foo
  fi

# Run tests
test:
	foo

# Watch the test files for changes and run tests when needed
watch_and_test:
	foo

.PHONY: clean configs deps deps_clean migrate migrate_test prepare_folders prepare_db prepare_test_db run run_foreground seeds system_deps test watch_and_test