-- INSERTS Superadmin, Admin & All necessary roles and privileges. Execute this script AFTER running the web api once.

INSERT INTO public.users (id, is_active, email, first_name, last_name, password, user_name, created_on) VALUES ('fade0622-d157-45ba-bcbe-6845b4592bf5', true, 'Superadmin@Admin.com', 'Superadmin', 'Superadmin', '$2a$10$Ml3goLI9RY8cgiENy8oZleoY8oK89ghkF8neOExGcx30FLKAL0.EW', NULL, CURRENT_TIMESTAMP);
INSERT INTO public.users (id, is_active, email, first_name, last_name, password, user_name, created_on) VALUES ('308a9732-ecda-4e11-b330-4001adaf762f', true, 'Admin@Admin.com', 'Admin', 'Admin', '$2a$10$GBACrhQ2lYAry0I2Us0SA.RO0OudXPUh1tnUS.5nxSomoqeq13nd2', NULL, CURRENT_TIMESTAMP);


INSERT INTO public.privileges (id, is_active, title, created_on) VALUES ('28780d80-b4c8-41f3-bb51-39935baa37ca', true, 'READ_PRIVILEGE', CURRENT_TIMESTAMP);
INSERT INTO public.privileges (id, is_active, title, created_on) VALUES ('aa710dbc-307b-4f6c-b44e-727ff825d70c', true, 'WRITE_PRIVILEGE', CURRENT_TIMESTAMP);


INSERT INTO public.roles (id, is_active, title, created_on) VALUES ('dbd14113-0e17-4522-8daa-93e6f9fb13c9', true, 'ROLE_SUPERADMIN',CURRENT_TIMESTAMP);
INSERT INTO public.roles (id, is_active, title, created_on) VALUES ('f52b378c-8fb0-439c-a574-5031ee3e767c', true, 'ROLE_ADMIN',CURRENT_TIMESTAMP);
INSERT INTO public.roles (id, is_active, title, created_on) VALUES ('4868f6ce-4f25-40b0-9e3f-f10833bd3f9c', true, 'ROLE_PERSONNEL',CURRENT_TIMESTAMP);


INSERT INTO public.role_privileges (role_id, privilege_id) VALUES ('dbd14113-0e17-4522-8daa-93e6f9fb13c9', '28780d80-b4c8-41f3-bb51-39935baa37ca');
INSERT INTO public.role_privileges (role_id, privilege_id) VALUES ('dbd14113-0e17-4522-8daa-93e6f9fb13c9', 'aa710dbc-307b-4f6c-b44e-727ff825d70c');
INSERT INTO public.role_privileges (role_id, privilege_id) VALUES ('f52b378c-8fb0-439c-a574-5031ee3e767c', '28780d80-b4c8-41f3-bb51-39935baa37ca');
INSERT INTO public.role_privileges (role_id, privilege_id) VALUES ('f52b378c-8fb0-439c-a574-5031ee3e767c', 'aa710dbc-307b-4f6c-b44e-727ff825d70c');
INSERT INTO public.role_privileges (role_id, privilege_id) VALUES ('4868f6ce-4f25-40b0-9e3f-f10833bd3f9c', '28780d80-b4c8-41f3-bb51-39935baa37ca');


INSERT INTO public.user_roles (user_id, role_id) VALUES ('fade0622-d157-45ba-bcbe-6845b4592bf5', 'dbd14113-0e17-4522-8daa-93e6f9fb13c9');
INSERT INTO public.user_roles (user_id, role_id) VALUES ('308a9732-ecda-4e11-b330-4001adaf762f', 'f52b378c-8fb0-439c-a574-5031ee3e767c');