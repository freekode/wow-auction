# wow-auction
Auction statistics for World of Warcraft

Add to your tomcat/conf/server.xml

```xml
<Resource name="jdbc/WoWAuctionDB"
          global="jdbc/WoWAuctionDB"
          auth="Container"
          type="javax.sql.DataSource"
          driverClassName="org.postgresql.Driver"
          url="jdbc:postgresql://localhost:5432/<DATABASE>"
          username="<USERNAME>"
          password="<PASSWORD>"
          maxActive="100"
          maxIdle="20"
          minIdle="5"
          maxWait="10000"/>
```

