## How to
- If running windows please make sure to uncomment the following lines form the `Dockerfile`
  - `RUN apt-get install dos2unix`
  - `RUN dos2unix ./gradlew`
- Execute `docker-compse up -d` from CLI to run application.
  - It could be that the `spring-api-test_api-1` doesn't start because it needs to database is not started. In that case start it again with `docker-compose up -d`.
- When application is running you can execute the following API calls
  - To create a Shop with API key:
    - POST http://localhost:8080/shop/create
    - BODY:
      - `{
        "name": "trackbee"
        }`
  - It will give you an API key save this API key
  - Now you can place an order with the following call:
    - POST http://localhost:8080/order/1 (replace 1 with your shop id)
    - HEADER:
      - `X-Authorization=YOUR_API_KEY_HERE`
    - BODY:
      -  `{
         "id": "#1111111",
         "price": 20.25,
         "timestamp": 1721768371
         }`
  - The order of the shop is now saved in the database and send to all PlatformTypes

## TODO/Improvements
- The rest of the assignment
- Retrieve shop id based on API key with order call instead of fetching it form the path
- Make sure shop create is for "admins" only with their own api key (like with the resend call)
- Make docker-compose file so database has a healthcheck, so api starts up when db is fully ready
