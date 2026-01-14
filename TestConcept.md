| ÄquivalenzKlasse                         | Expected Return |                                                                        Side Effects | 
|:-----------------------------------------|:---------------:|------------------------------------------------------------------------------------:|
| 1. Item is null                          |      false      |                                                     item not added in the inventory |
| 2. Weight exceeds max allowed weight     |  returns false  |                                                     item not added in the inventory |
| 3. Item is cursed, player already cursed |  returns true   |                   player's sanity reduces, extra penalty, item added into inventory |
| 4. Item cursed, player not               |  returns true   |                player's sanity reduces, no extra penalty, item added into inventory | 
| 5. Normal item, weight Ok                |  returns true   |                                          no side effects, item added into inventory | 


