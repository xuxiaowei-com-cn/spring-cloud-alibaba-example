# Set shell to bash for better compatibility
SHELL := /usr/bin/env bash

# Set default target when running 'make' without arguments
.DEFAULT_GOAL := help

# Display help information with colored output
# Parses comments with '##' and formats them nicely
help: ## Show help information
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z0-9._-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

# Maven command configuration
# Prefer using system mvn, fall back to ./mvnw wrapper if mvn does not exist
MVN ?= $(shell command -v mvn >/dev/null 2>&1 && echo "mvn" || echo "./mvnw")
# Maven arguments: -T 4C (parallel build with 4 threads per CPU core)
#                  -e (show error stack traces)
#                  -B (batch mode, non-interactive)
#                  -V (show version information)
MAVEN_ARGS ?= -T 4C -e -B -V

# Mark targets as phony (not actual files)
.PHONY: help \
	format clean deep-clean package build \
	package-with-tests test \
	verify \
	dependency-tree dependency-updates \
	run-nacos run-seata run-sentinel \
	run-gateway-webflux run-gateway-webmvc \
	run-user run-user-actuator run-user-rocketmq run-user-schedulerx run-user-seata run-user-sentinel \
	test-gateway-webflux test-gateway-webmvc \
	test-user test-user-actuator test-user-rocketmq test-user-schedulerx test-user-seata test-user-sentinel \
	test-integration-2025.1.x cleanup-integration-2025.1.x

format: ## Format Java code with spring-javaformat
	$(MVN) $(MAVEN_ARGS) spring-javaformat:apply

clean: ## Clean the project
	$(MVN) $(MAVEN_ARGS) clean

deep-clean: clean ## Deep clean the project (mvn clean + remove runtime files)
	@echo "Cleaning additional files and directories..."
	@-rm -rf file_store
	@-rm -rf logs
	@-rm -rf derby.log
	@-rm -rf nacos-bootstrap-3.x/logs
	@-rm -rf nacos-bootstrap-3.x/work
	@-rm -rf nacos-bootstrap-3.x/derby.log
	@-rm -rf seata-server-2.x/file_store
	@-rm -rf seata-server-2.x/sessionStore
	@echo "Deep cleanup completed."

package: ## Build Maven project (skip tests)
	@echo "Building ..."
	$(MVN) $(MAVEN_ARGS) clean package -DskipTests

build: package ## Build Maven project (skip tests)

package-with-tests: ## Build Maven project (with tests)
	@echo "Building with tests ..."
	$(MVN) $(MAVEN_ARGS) clean package

test: ## Run tests
	@echo "Running tests ..."
	$(MVN) $(MAVEN_ARGS) clean test

verify: ## Verify the project
	@echo "Verifying project ..."
	$(MVN) $(MAVEN_ARGS) clean verify

dependency-tree: ## Display dependency tree
	@echo "Displaying dependency tree ..."
	$(MVN) $(MAVEN_ARGS) dependency:tree

dependency-updates: ## Check for dependency updates
	@echo "Checking for dependency updates ..."
	$(MVN) $(MAVEN_ARGS) versions:display-dependency-updates

run-nacos: ## Run nacos-bootstrap-3.x module
	NACOS_CREATE_TOKEN=true $(MVN) clean spring-boot:run -pl nacos-bootstrap-3.x

run-seata: ## Run seata-server-2.x module
	REGISTRY_PREFERREDNETWORKS=172.25.25.* $(MVN) clean spring-boot:run -pl seata-server-2.x

run-sentinel: ## Run sentinel-dashboard-1.x module
	$(MVN) clean spring-boot:run -pl sentinel-dashboard-1.x

run-gateway-webflux: ## Run spring-cloud-2025.1.x/gateway-server-webflux module
	$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/gateway-server-webflux

run-gateway-webmvc: ## Run spring-cloud-2025.1.x/gateway-server-webmvc module
	$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/gateway-server-webmvc

run-user: ## Run spring-cloud-2025.1.x/user module
	$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user

run-user-actuator: ## Run spring-cloud-2025.1.x/user-actuator module
	$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user-actuator

run-user-rocketmq: ## Run spring-cloud-2025.1.x/user-rocketmq module
	$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user-rocketmq

run-user-schedulerx: ## Run spring-cloud-2025.1.x/user-schedulerx module
	$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user-schedulerx

run-user-seata: ## Run spring-cloud-2025.1.x/user-seata module
	$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user-seata

run-user-sentinel: ## Run spring-cloud-2025.1.x/user-sentinel module
	$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user-sentinel

test-gateway-webflux: ## Test spring-cloud-2025.1.x/gateway-server-webflux module
	$(MVN) $(MAVEN_ARGS) clean test -pl spring-cloud-2025.1.x/gateway-server-webflux

test-gateway-webmvc: ## Test spring-cloud-2025.1.x/gateway-server-webmvc module
	$(MVN) $(MAVEN_ARGS) clean test -pl spring-cloud-2025.1.x/gateway-server-webmvc

test-user: ## Test spring-cloud-2025.1.x/user module
	$(MVN) $(MAVEN_ARGS) clean test -pl spring-cloud-2025.1.x/user

test-user-actuator: ## Test spring-cloud-2025.1.x/user-actuator module
	$(MVN) $(MAVEN_ARGS) clean test -pl spring-cloud-2025.1.x/user-actuator

test-user-rocketmq: ## Test spring-cloud-2025.1.x/user-rocketmq module
	$(MVN) $(MAVEN_ARGS) clean test -pl spring-cloud-2025.1.x/user-rocketmq

test-user-schedulerx: ## Test spring-cloud-2025.1.x/user-schedulerx module
	$(MVN) $(MAVEN_ARGS) clean test -pl spring-cloud-2025.1.x/user-schedulerx

test-user-seata: ## Test spring-cloud-2025.1.x/user-seata module
	$(MVN) $(MAVEN_ARGS) clean test -pl spring-cloud-2025.1.x/user-seata

test-user-sentinel: ## Test spring-cloud-2025.1.x/user-sentinel module
	$(MVN) $(MAVEN_ARGS) clean test -pl spring-cloud-2025.1.x/user-sentinel

test-integration-2025.1.x: ## Run integration test for spring-cloud-2025.1.x
	@echo "Starting integration test for spring-cloud-2025.1.x..."
	@echo "Step 1/12: Starting nacos-bootstrap-3.x..."
	@NACOS_CREATE_TOKEN=true $(MVN) clean spring-boot:run -pl nacos-bootstrap-3.x > ./tmp/nacos.log 2>&1 & echo $$! > ./tmp/nacos.pid
	@sleep 30
	@echo "Step 2/12: Starting seata-server-2.x..."
	@REGISTRY_PREFERREDNETWORKS=172.25.25.* $(MVN) clean spring-boot:run -pl seata-server-2.x > ./tmp/seata.log 2>&1 & echo $$! > ./tmp/seata.pid
	@sleep 20
	@echo "Step 3/12: Starting sentinel-dashboard-1.x..."
	@$(MVN) clean spring-boot:run -pl sentinel-dashboard-1.x > ./tmp/sentinel.log 2>&1 & echo $$! > ./tmp/sentinel.pid
	@sleep 20
	@echo "Step 4/12: Starting gateway-server-webflux..."
	@$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/gateway-server-webflux > ./tmp/gateway-webflux.log 2>&1 & echo $$! > ./tmp/gateway-webflux.pid
	@sleep 15
	@echo "Step 5/12: Starting gateway-server-webmvc..."
	@$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/gateway-server-webmvc > ./tmp/gateway-webmvc.log 2>&1 & echo $$! > ./tmp/gateway-webmvc.pid
	@sleep 15
	@echo "Step 6/12: Starting user service..."
	@$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user > ./tmp/user.log 2>&1 & echo $$! > ./tmp/user.pid
	@sleep 15
	@echo "Step 7/12: Starting user-actuator service..."
	@$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user-actuator > ./tmp/user-actuator.log 2>&1 & echo $$! > ./tmp/user-actuator.pid
	@sleep 15
	@echo "Step 8/12: Starting user-rocketmq service..."
	@echo "Disable user-rocketmq service..."
	#@$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user-rocketmq > ./tmp/user-rocketmq.log 2>&1 & echo $$! > ./tmp/user-rocketmq.pid
	@sleep 15
	@echo "Step 9/12: Starting user-schedulerx service..."
	@$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user-schedulerx > ./tmp/user-schedulerx.log 2>&1 & echo $$! > ./tmp/user-schedulerx.pid
	@sleep 15
	@echo "Step 10/12: Starting user-seata service..."
	@$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user-seata > ./tmp/user-seata.log 2>&1 & echo $$! > ./tmp/user-seata.pid
	@sleep 15
	@echo "Step 11/12: Starting user-sentinel service..."
	@$(MVN) clean spring-boot:run -pl spring-cloud-2025.1.x/user-sentinel > ./tmp/user-sentinel.log 2>&1 & echo $$! > ./tmp/user-sentinel.pid
	@sleep 15
	@echo "Step 12/12: Running tests..."
	@$(MVN) $(MAVEN_ARGS) test -pl spring-cloud-2025.1.x/gateway-server-webflux
	@$(MVN) $(MAVEN_ARGS) test -pl spring-cloud-2025.1.x/gateway-server-webmvc
	@$(MVN) $(MAVEN_ARGS) test -pl spring-cloud-2025.1.x/user
	@$(MVN) $(MAVEN_ARGS) test -pl spring-cloud-2025.1.x/user-actuator
	#@$(MVN) $(MAVEN_ARGS) test -pl spring-cloud-2025.1.x/user-rocketmq
	@$(MVN) $(MAVEN_ARGS) test -pl spring-cloud-2025.1.x/user-schedulerx
	@$(MVN) $(MAVEN_ARGS) test -pl spring-cloud-2025.1.x/user-seata
	@$(MVN) $(MAVEN_ARGS) test -pl spring-cloud-2025.1.x/user-sentinel

cleanup-integration: ## Cleanup integration test processes
	@echo "Stopping all services..."
	@-kill `cat ./tmp/user-sentinel.pid 2>/dev/null` 2>/dev/null || true
	@-kill `cat ./tmp/user-seata.pid 2>/dev/null` 2>/dev/null || true
	@-kill `cat ./tmp/user-schedulerx.pid 2>/dev/null` 2>/dev/null || true
	@-kill `cat ./tmp/user-rocketmq.pid 2>/dev/null` 2>/dev/null || true
	@-kill `cat ./tmp/user-actuator.pid 2>/dev/null` 2>/dev/null || true
	@-kill `cat ./tmp/user.pid 2>/dev/null` 2>/dev/null || true
	@-kill `cat ./tmp/gateway-webmvc.pid 2>/dev/null` 2>/dev/null || true
	@-kill `cat ./tmp/gateway-webflux.pid 2>/dev/null` 2>/dev/null || true
	@-kill `cat ./tmp/sentinel.pid 2>/dev/null` 2>/dev/null || true
	@-kill `cat ./tmp/seata.pid 2>/dev/null` 2>/dev/null || true
	@-kill `cat ./tmp/nacos.pid 2>/dev/null` 2>/dev/null || true
	@-rm -f ./tmp/*.pid ./tmp/*.log 2>/dev/null || true
	@echo "Cleanup completed."
