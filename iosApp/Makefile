PROJECT_FILE := iosApp/iosApp.xcodeproj
SCHEME_NAME_APP := DroidKnights

.PHONY: setup
setup:
	brew bundle --no-lock

.PHONY: open
open:
	open $(PROJECT_FILE)

.PHONY: build-app-module
build-app-module:
	cd .. && \
	./gradlew composeApp:assembleSharedXCFramework --no-configuration-cache

.PHONY: build-app-debug
build-app-debug: build-app-module
	set -o pipefail && \
		xcodebuild -project $(PROJECT_FILE) \
		-scheme $(SCHEME_NAME_APP) \
		-configuration Debug \
		-skipPackagePluginValidation \
		-skipMacroValidation \
		-destination "platform=iOS Simulator,name=iPhone 16 Pro,OS=18.1" \
		clean build

.PHONY: build-app-debug-x64
build-app-debug: build-app-module
	set -o pipefail && \
		xcodebuild -project $(PROJECT_FILE) \
		-scheme $(SCHEME_NAME_APP) \
		-configuration Debug \
		-skipPackagePluginValidation \
		-skipMacroValidation \
		-destination "platform=iOS Simulator,name=iPhone 14 Pro,OS=16.4" \
		clean build

# Gradle Utility
.PHONY: gradle-clean
gradle-clean:
	cd .. && \
	./gradlew --stop && ./gradlew clean
