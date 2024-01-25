-- database init
CREATE DATABASE ehotel
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE ehotel
    IS 'db for ehotel project csi2132';