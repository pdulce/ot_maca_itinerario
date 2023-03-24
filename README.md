# mac1

## Descripción
Proyecto general para mac1


## Versión

## Prerequisitos

Se necesita tener instalado y configurado:
- mvn: 3.5.0 y configurado apuntando al repo de GISS
- java: 17
- docker
- giss-core: 2.0.0


## Uso

Para la compilación del servicio bastará con ejecutar
```bash
mvn clean install
```

Para ejecutar el servicio 

```bash
mvn clean install
```

Ejecución en local
```bash
mvn spring-boot:run -Dspring.profiles.active=local
```

También se puede lanzar directamente el artefacto con java
```bash
java -jar  .\target\ot_maca_itinerario-1.0.0-SNAPSHOT.jar
```



## Diseño de API 


## Configuración y entornos

_Descripción de propiedades configurables en el servicio_

## Testing

## Contacto

Para cualquier problema o sugerencia: [ODT-CORE.GISS-SSCC.DED@seg-social.es](mailto:ODT-CORE.GISS-SSCC.DED@seg-social.es)

Para crear este repo se ha usado esta secuencia de comandos git:
git init
git add README.md
git commit -m "first commit itinerary"
git branch -M main
git remote add origin git@github.com:pdulce/ot_maca_itinerario.git
git push -u origin main

Para crear una ssh key de nuevo colaborador:
ssh-keygen -t ed25519 -C "<mail-colaborador>"