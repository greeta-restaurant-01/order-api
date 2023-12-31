# rebuild and start

docker-compose down

mvn clean install -DskipTests

cd customer-service/customer-container
mvn spring-boot:build-image -DskipTests \
  -Dspring-boot.build-image.imageName=customer-service

cd ../../restaurant-service/restaurant-container
mvn spring-boot:build-image -DskipTests \
  -Dspring-boot.build-image.imageName=restaurant-service

cd ../../payment-service/payment-container
mvn spring-boot:build-image -DskipTests \
  -Dspring-boot.build-image.imageName=payment-service

cd ../../order-service/order-container
mvn spring-boot:build-image -DskipTests \
  -Dspring-boot.build-image.imageName=order-service

cd ../../gateway-service
mvn spring-boot:build-image -DskipTests \
  -Dspring-boot.build-image.imageName=gateway-service

cd ../

docker-compose up -d