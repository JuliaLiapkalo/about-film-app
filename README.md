# About Film Application

The 'About Film Application' is a console-based program designed to parse a list of JSON files containing
data related to a primary entity and generate statistics based on one of its attributes. The program allows
users to specify the path to the folder containing JSON files and the attribute on which statistics should be
generated. It supports multiple attributes, including text attributes with multiple values.



## Functionality

- Parses JSON files in the specified folder related to the primary entity (Film).
- Generates statistics based on the specified attribute (e.g., Name, Year of release, Genres).
- Supports multiple attributes for generating statistics.
- Outputs the statistics in an XML file sorted by the number of occurrences from largest to smallest.
- The name of the XML file is statistics_by_{attribute}.xml.


## Entities

`Primary Entity: Films`

`Secondary Entity: Directors`


## Attributes:

**Film:**
Name,
Genre,
Year of release,
List of main actors,
Director;

**Director:**
Name,
Age;


## Examples of input and output files

 - [Input](https://github.com/JuliaLiapkalo/about-film-app/tree/master/src/main/resources/films)
 - [Output](https://github.com/JuliaLiapkalo/about-film-app/blob/master/statistics_by_mainActors.xml)



## Performance test with multiple threads

 - Pool size 1: 338 milliseconds
 - Pool size 2: 51 milliseconds
 - Pool size 4: 17 milliseconds
 - Pool size 8: 15 milliseconds

The performance test results demonstrate the impact of thread pool size on the efficiency of parsing JSON files. 
As the pool size increases from 1 to 8, the time taken to parse the files decreases significantly.
Specifically, with a pool size of 1, the execution time is 338 milliseconds, indicating that files are parsed 
sequentially, one after another. However, when the pool size is increased to 2, 4, and 8, the execution time drops to 
51, 17, and 15 milliseconds, respectively. This reduction in time suggests that parallelizing file parsing across
multiple threads leads to substantial performance improvements.

Performance test by path: src/test/java/com/liapkalo/profitsoft/directorfilm/perfomence/JsonParseMultiThreadTest.java


