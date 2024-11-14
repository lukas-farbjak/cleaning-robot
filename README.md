# Cleaning Robot

Solution of MyQ Unattended Coding Test
```
MyQ has decided to launch a new automated cleaning robot to the market. The robot shall be able to clean all surfaces
in a room, automatically, without manual intervention."
```
More description is in the document "MyQ Unattended Coding Test 2.5.pdf"

## Requirements
* Java 21 or higher
* Maven

## Building
Build project in maven
```
mvn clean verify
```

## Running Program
After build run created jar file cleaning_robot.java in Java with two arguments.
* The first argument is path for input json file, which must be provided as input with instructions for robot
* The second argument is path for output json file, which will be generated as result by program
```
java cleaning_robot <input.json> <output.json>
```

## Input JSON Format:
```json
{
  "map": [
    ["S", "S", "S", "S"],
    ["S", "S", "C", "S"],
    ["S", "S", "S", "S"],
    ["S", "null", "S", "S"]
  ],
  "start": {"X": 3, "Y": 0, "facing": "N"},
  "commands": [ "TL","A","C","A","C","TR","A","C"],
  "battery": 80
}
```

## Output JSON Format:
```json
{
  "visited" : [{ "X" : 1, "Y" : 0 },{ "X" : 2, "Y" : 0 },{ "X" : 3, "Y" : 0 }],
  "cleaned" : [{ "X" : 1, "Y" : 0 },{ "X" : 2, "Y" : 0 }],
  "final" : { "X" : 2, "Y" : 0, "facing" : "N"},
  "battery" : 53
}
```