### Data model for automobile parts compatibility

#### Premise
If I want to check if an automobile has to be assembled, then I have 
to provide the list of details types the automobile consists of.
Then using the types, I need to scan the "inventory" to check if 
all the requested details are available in the system, and they are compatible 
with each other at th same time.

#### Solution
The first naive approach is to use a relational database and model the compatibility
with a table as follows:

Table: `detail_compatibility_mapping`

| Field name | Field type | Constraints               |
|------------|------------|---------------------------|
| detail1_id | UUID       | Foreign key; composite PK |
| detail2_id | UUID       | Foreign key; composite PK |

E.g. we are given the list of detail IDs, then checking the compatibility will look like:
```
with ids as (select * from (values (d1), (d2), (d3), ..., d(n)) as ids (id)),
     pairs as (select d1.id id1, d2.id id2 from ids d1 join ids d2 on d1.id != d2.id)
select * from detail_compatibility_mapping
where (id1, id2) in (select id1, id2 from pairs)
```

With such an approach, reading of data should be fast (index scan) even if the data volume is high.

However, if new parts are added and old parts removed from the catalog at high frequency,
the table will be extensively locked; so the proposed trivial approach doesn't seem viable in this case.

What I would consider instead is using a graph database with a model like:
* an automobile part is a node;
* the nodes are linked using "compatible with" relationship.

I don't have good experience with graph databases, but I would research this approach further;
at first glance, it looks viable because a graph represents the compatibility of automobile parts
very well.