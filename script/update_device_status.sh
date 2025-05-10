#!/bin/bash

# Variables
SERIAL_NUMBER="SN-47F2B3CE"
ACTIVE=false
PORT=8180
BASE_URL="http://localhost:$PORT/api/v1/devices"

# Send PUT request
curl -X PUT "$BASE_URL/$SERIAL_NUMBER/status?active=$ACTIVE"
