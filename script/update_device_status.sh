#!/bin/bash

# Variables
SERIAL_NUMBER="SN-E0E62F1D"
ACTIVE=true
PORT=80
BASE_URL="http://device-registry-alb-backend-5-918131464.ap-southeast-1.elb.amazonaws.com:$PORT/api/v1/devices"

# Send PUT request
curl -X PUT "$BASE_URL/$SERIAL_NUMBER/status?active=$ACTIVE"
