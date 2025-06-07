#!/bin/bash

# 사용법 안내
if [ $# -ne 1 ]; then
  echo "사용법: $0 [아이콘 PNG 파일명]"
  echo "예: $0 icon.png"
  exit 1
fi

INPUT_PNG=$1
ICON_NAME="${INPUT_PNG%.*}"        # 확장자 제거
ICONSET_DIR="${ICON_NAME}.iconset"

# iconset 디렉토리 만들기
mkdir -p "$ICONSET_DIR"

# 다양한 크기로 변환
sips -z 16 16     "$INPUT_PNG" --out "$ICONSET_DIR/icon_16x16.png"
sips -z 32 32     "$INPUT_PNG" --out "$ICONSET_DIR/icon_16x16@2x.png"
sips -z 32 32     "$INPUT_PNG" --out "$ICONSET_DIR/icon_32x32.png"
sips -z 64 64     "$INPUT_PNG" --out "$ICONSET_DIR/icon_32x32@2x.png"
sips -z 128 128   "$INPUT_PNG" --out "$ICONSET_DIR/icon_128x128.png"
sips -z 256 256   "$INPUT_PNG" --out "$ICONSET_DIR/icon_128x128@2x.png"
sips -z 256 256   "$INPUT_PNG" --out "$ICONSET_DIR/icon_256x256.png"
sips -z 512 512   "$INPUT_PNG" --out "$ICONSET_DIR/icon_256x256@2x.png"
sips -z 512 512   "$INPUT_PNG" --out "$ICONSET_DIR/icon_512x512.png"
sips -z 1024 1024 "$INPUT_PNG" --out "$ICONSET_DIR/icon_512x512@2x.png"

# .icns 생성
iconutil -c icns "$ICONSET_DIR"

# 결과 안내
echo "✅ ${ICON_NAME}.icns 파일이 생성되었습니다."

# iconset 폴더 삭제 여부 질문
read -p "iconset 폴더(${ICONSET_DIR})를 삭제할까요? [y/N]: " confirm
if [[ "$confirm" == "y" || "$confirm" == "Y" ]]; then
  rm -r "$ICONSET_DIR"
  echo "🗑️ iconset 폴더 삭제 완료"
else
  echo "📁 iconset 폴더를 유지했습니다"
fi