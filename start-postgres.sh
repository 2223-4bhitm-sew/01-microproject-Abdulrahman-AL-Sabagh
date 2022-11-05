docker run --rm=true \
           --name postgres \
           -e POSTGRES_USER=postgres \
           -e POSTGRES_PASSWORD=postgres \
           -e POSTGRES_DB=db \
           -v ${PWD}/db-postgres/db:/var/lib/postgresql/data \
           -p 5432:5432 \
           postgres:14.5-alpine
