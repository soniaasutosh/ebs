#Digital platform for Electricity Billing System

#Challenges (Problem Statement)
#Offline Metre - Periodically Manually Tracking (Customer/Company)
#Metre Damage
#Not possible to get used units
#Not possible informed instantly
#unaware excessive usage
#Customer loose
#Legal offence
#Manual Bill Generate
#Manual Bill Payment
#Solutions 
#Online Metre - Usage/Conditions statistics  
#Maintain Usage History
#Monthly Auto Bill generation
#Notification
#Send Online Bill
#Alert User 
#Online Bill Payment



Build

mvnw clean package

docker build -t soniaasu/ebsapi:05102022 .

docker network create my-ebs-net

docker run --rm --name ebs-mysql --network my-ebs-net  -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:8.0.30

docker run --rm -p 8080:8080 --network my-ebs-net -e spring.datasource.url=jdbc:mysql://ebs-mysql:3306/EBS --name ebsapi soniaasu/ebsapi:05102022 
 

