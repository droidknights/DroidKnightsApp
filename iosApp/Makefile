PROJECT_FILE := iosApp.xcodeproj
SCHEME_NAME_APP := iosApp

.PHONY: setup
setup:
	brew bundle --no-lock

.PHONY: open
open:
	open $(PROJECT_FILE)

.PHONY: build-app-debug
build-app-debug:
	set -o pipefail && \
		xcodebuild -project $(PROJECT_FILE) \
		-scheme $(SCHEME_NAME_APP) \
		-configuration Debug \
		-skipPackagePluginValidation \
		-skipMacroValidation \
		-destination "platform=iOS Simulator,name=iPhone 16 Pro,OS=18.1" \
		clean build

.PHONY: build-app-debug-x64
build-app-debug-x64:
	set -o pipefail && \
		xcodebuild -project $(PROJECT_FILE) \
		-scheme $(SCHEME_NAME_APP) \
		-configuration Debug \
		-skipPackagePluginValidation \
		-skipMacroValidation \
		-allowProvisioningUpdates \
		-destination "platform=iOS Simulator,name=iPhone 15 Pro,OS=17.2" \
		clean build

# Gradle Utility
.PHONY: gradle-clean
gradle-clean:
	cd .. && \
	./gradlew --stop && ./gradlew clean
