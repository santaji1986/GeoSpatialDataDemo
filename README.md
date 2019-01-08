# GeoSpatialDataDemo
Demonstrate GeoSpatial Image download using Spring-boot RESTController

few default values defined in `application.properties` of project like `server.port` and `dataDir` and logback.xml 
 - `server.port` - is defaultly initialized to `8080`
 - `dataDir` - is defaultly initialized to `${user.home}/interstellar` this directory holds all .tif files which can be downloaded based on user input
 - log directory is initialized to  "interstellar" directory under home directory of current user.
  
# How to compile project ?
`mvn clean package`
# How to run project ?
`java -jar target\geospatial.jar`
# Test url 
  *GET* http://localhost:8080/test?hello=interstellar

# Test Image downlod url
*POST* http://localhost:8080/generate-images
- Header -  
    `Content-Type=application/json`
- BODY -
```json
{
    "utmZone": 33,
    "latitudeBand": "U",
    "gridSquare": "UP",
    "date": "2018-08-08",
    "channelMap": "visible"
}
```
