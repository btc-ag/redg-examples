# RedG with Oracle DB

This example shows how to use RedG with an Oracle database. It includes an example on how to use the custom type mapping via JSON files
and the data set code shows how to use the M:N relation convenience methods.
 
# Prerequisites

Obtain Oracle JDBC drivers and install them in your local Maven repository (or use a private Maven repository that contains the drivers). Adapt the `pom.xml` if necessary.

Run the content of the `schema.sql` script in your Oracle database. This will create the necessary tables for this example.