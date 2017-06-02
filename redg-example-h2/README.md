# RedG with H2 databases

This is an example project showing how RedG can work with H2 databases.
It also showcases how one can utilize the `PluggableDefaultValueStrategy` with two custom 
`PluggableDefaultValueProvider`s to get random valid credit card numbers or ISO country codes as default values.

## Note

This example uses a H2 file database called `database` in the project root folder. Tests have to be 
executed with Maven, as the `schema.sql` script clears the database and (re)creates the tables.
If you not clear the db before each test run, RedG will violate primary keys (index restarts at 0).