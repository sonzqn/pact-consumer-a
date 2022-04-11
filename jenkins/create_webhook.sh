curl 'http://localhost:9292/webhooks/provider/provider-x/consumer/consumer-a' \
  -H 'Accept: application/hal+json, application/json, */*; q=0.01' \
  -H 'Content-Type: application/json' \
  -H 'X-Interface: HAL Browser' \
  --data-raw $'{\n  "consumer": {\n    "name": "consumer-a"\n  },\n  "provider": {\n    "name": "provider-x"\n  },\n  "request": {\n    "method": "POST",\n    "url": "http://127.0.0.1:8080/job/provider-x-run-contract-tests/buildWithParameters?pactConsumerTags=${pactbroker.consumerVersionTags}&pactConsumerName=${pactbroker.consumerName}",\n    "headers": {\n      "Accept": "application/json",\n      "Authorization": "Basic c29ubmQ0OjExNWJhNzcyNjg0OGU5ZTJmZWYzZGU5MzdmODJkZDU2Mzg="\n    }\n  },\n  "events": [\n    {\n      "name": "contract_content_changed"\n    }\n  ]\n}\n' \
  --compressed