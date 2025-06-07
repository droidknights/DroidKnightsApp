#!/bin/bash

# 사용법 안내
if [ $# -ne 1 ]; then
  echo "사용법: $0 [아이콘 PNG 파일명]"
  echo "예: $0 icon.png"
  exit 1
fi

INPUT_PNG=$1
BASENAME="${INPUT_PNG%.*}"

# 요구 툴 확인
if ! command -v convert &> /dev/null; then
  echo "❌ ImageMagick의 'convert' 명령어가 설치되어 있어야 합니다."
  echo "macOS: brew install imagemagick"
  exit 1
fi

# 임시 폴더 생성
TMP_DIR="${BASENAME}_ico_temp"
mkdir -p "$TMP_DIR"

# 여러 크기로 PNG 생성
for size in 16 32 48 64 128 256; do
  convert "$INPUT_PNG" -resize ${size}x${size} "$TMP_DIR/icon_${size}x${size}.png"
done

# PNG들 모아 ICO 생성
convert "$TMP_DIR"/*.png "${BASENAME}.ico"

# 완료 안내
echo "✅ ${BASENAME}.ico 파일이 생성되었습니다."

# 임시 폴더 삭제 여부
read -p "임시 폴더를 삭제할까요? [y/N]: " confirm
if [[ "$confirm" =~ ^[Yy]$ ]]; then
  rm -r "$TMP_DIR"
  echo "🗑️ 삭제 완료"
else
  echo "📁 폴더 유지됨: $TMP_DIR"
fi