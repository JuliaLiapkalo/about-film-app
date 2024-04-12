# About Film Application

The About Film Application is a console-based program designed to parse a list of JSON files containing
data related to a primary entity and generate statistics based on one of its attributes. The program allows
users to specify the path to the folder containing JSON files and the attribute on which statistics should be
generated. It supports multiple attributes, including text attributes with multiple values.

## Entities:
Primary Entity: Films
Secondary Entity: Directors

## Attributes of Film:
Name,
Genre,
Year of release,
List of main actors,
Director;

##Attributes of Director:
Name,
Age;

## Functionality:

Parses JSON files in the specified folder related to the primary entity (Film).
Generates statistics based on the specified attribute (e.g., Name, Year of release, Genres).
Supports multiple attributes for generating statistics.
Outputs the statistics in an XML file sorted by the number of occurrences from largest to smallest.
The name of the XML file is statistics_by_{attribute}.xml.

