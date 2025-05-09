#!/bin/bash
set -e

echo "Installing Git pre-commit hook..."
cp hooks/pre-commit .git/hooks/pre-commit
chmod +x .git/hooks/pre-commit
echo "Done! ✅🎉"