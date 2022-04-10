ALTER TABLE connection_config_overrides
  ALTER COLUMN updated_at DROP NOT NULL,
  ALTER COLUMN deleted_at DROP NOT NULL;
