-- Insertion des donnees de base dans la table des habilitations
insert into BAPP_HABILITATIONS(HABILITATION_ID, HABILITATION_CODE, HABILITATION_LABEL) VALUES (1, 'users.list', 'Utilisateurs - Voir');
insert into BAPP_HABILITATIONS(HABILITATION_ID, HABILITATION_CODE, HABILITATION_LABEL) VALUES (2, 'users.create', 'Utilisateurs - Creer');
insert into BAPP_HABILITATIONS(HABILITATION_ID, HABILITATION_CODE, HABILITATION_LABEL) VALUES (3, 'users.edit', 'Utilisateurs - Modifier');
insert into BAPP_HABILITATIONS(HABILITATION_ID, HABILITATION_CODE, HABILITATION_LABEL) VALUES (4, 'users.delete', 'Utilisateurs - Supprimer');
insert into BAPP_HABILITATIONS(HABILITATION_ID, HABILITATION_CODE, HABILITATION_LABEL) VALUES (5, 'profiles.list', 'Profils - Voir');
insert into BAPP_HABILITATIONS(HABILITATION_ID, HABILITATION_CODE, HABILITATION_LABEL) VALUES (6, 'profiles.create', 'Profils - Creer');
insert into BAPP_HABILITATIONS(HABILITATION_ID, HABILITATION_CODE, HABILITATION_LABEL) VALUES (7, 'profiles.edit', 'Profils - Modifier');
insert into BAPP_HABILITATIONS(HABILITATION_ID, HABILITATION_CODE, HABILITATION_LABEL) VALUES (8, 'profiles.delete', 'Profils - Supprimer');

-- Creation du profil administrateur
insert into BAPP_PROFILES(PROFILE_ID, PROFILE_LIBELLE, PROFILE_DESC) values (1, 'Administrateur', 'Super admin avec tous les pouvoirs');

-- Association de toutes les habilitations au profil administrateur
insert into BAPP_PROFILE_HABILITATION(PRFHAB_PROFILE_ID, PRFHAB_HABILITATION_ID) values (1, 1);
insert into BAPP_PROFILE_HABILITATION(PRFHAB_PROFILE_ID, PRFHAB_HABILITATION_ID) values (1, 2);
insert into BAPP_PROFILE_HABILITATION(PRFHAB_PROFILE_ID, PRFHAB_HABILITATION_ID) values (1, 3);
insert into BAPP_PROFILE_HABILITATION(PRFHAB_PROFILE_ID, PRFHAB_HABILITATION_ID) values (1, 4);
insert into BAPP_PROFILE_HABILITATION(PRFHAB_PROFILE_ID, PRFHAB_HABILITATION_ID) values (1, 5);
insert into BAPP_PROFILE_HABILITATION(PRFHAB_PROFILE_ID, PRFHAB_HABILITATION_ID) values (1, 6);
insert into BAPP_PROFILE_HABILITATION(PRFHAB_PROFILE_ID, PRFHAB_HABILITATION_ID) values (1, 7);
insert into BAPP_PROFILE_HABILITATION(PRFHAB_PROFILE_ID, PRFHAB_HABILITATION_ID) values (1, 8);

-- Creation de l'utilisateur Super Admin avec profil Administrateur
insert into BAPP_USERS(USER_ID, USER_NOM, USER_PRENOM, USER_LOGIN, USER_PASSWORD, FK_PROFILE_ID) values (1, 'Super', 'Admin', 'admin@test.com', 'admin', 1);
