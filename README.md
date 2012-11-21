Cloud Computing - Zwitscher App
====================================

Dieses Repository beinhaltet alle Projekte der Zwitscher App.

Voraussetzungen: 
----------------
* JDK 1.6
* Eclipse Indigo SR2
* Checkstyle Plugin
* JBoss Tools Plugin
* EGit Plugin
* JBoss Application Server 6.1

Build Properties:
-----------------	
Für die ant Skripten existieren global-build.properties im Projekt "ZwitscherAppBuildEnv". Diese müssen individuell auf jedem Rechner, je nach Konfiguration angepasst werden. Speziell der Installationspfad des JBoss Application Server muss richtig gesetzt werden, da sonst der Build-Prozess fehlschlägt.

Es besteht die Möglichkeit die eingecheckten global-build.properties im Projekt "ZwitscherAppBuildEnv" zu überschreiben. Hierzu legt man eigene global-build.properties ins Verzeichnis <USER_HOME>/.ant/global-build.properties. Diese werden als erstes beim Build angezogen.

Build:
------
Die Zwitscher App lässt sich per ant bauen. Das zugehörige Skript liegt im Projekt "ZwitscherApp".

    ant -buildfile ZwitscherApp/build.xml complete-build

Deployment:
-----------
Für das automatische Deployment ist ebenfalls ein ant Skript vorhanden.

    ant -buildfile ZwitscherApp/dev-build.xml deploy
