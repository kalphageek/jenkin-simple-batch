# jenkins-simple-batch

## profile:local
* VM Options : -Dspring.profiles.active=local
* Program Arguments : --job.name=continuousJob version=1

#profile:postgres
* VM Options : -Dspring.profiles.active=postgres
* Program Arguments : --job.name=task1Job requestDate=20200801  