#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import sys
import json

def get():
    print("open")


if __name__ == '__main__':
    with open('./script/result.json', 'w', encoding='utf-8') as f:
        json.dump(str(sys.argv), f, ensure_ascii=False, indent=4)
