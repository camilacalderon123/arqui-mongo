const express = require('express');
const mongoose = require('mongoose');

// Configura la conexión con MongoDB Atlas
/* mongoose.connect('mongodb+srv://jarlinandresfb:Prueba123.@cluster0.ebqndcj.mongodb.net/test3?retryWrites=true&w=majority', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
})
  .then(() => console.log('Conexión exitosa a MongoDB Atlas'))
  .catch((error) => console.log('Error al conectar a MongoDB Atlas:', error)); */


// Crea la aplicación Express
const app = express();
app.use(express.json());

// Ruta POST para conectarse a Atlas y ademas crear la Database
  app.post('/createDatabase', async (req, res) => {
    try {
      const dbName = req.body.dbName;
  
      const connectionURL = `mongodb+srv://jarlinandresfb:Prueba123.@cluster0.ebqndcj.mongodb.net/${dbName}?retryWrites=true&w=majority`;
  
      await mongoose.connect(connectionURL, {
        useNewUrlParser: true,
        useUnifiedTopology: true,
      });
  
      res.status(200).json({
        message: `Conexión exitosa a MongoDB Atlas. Base de datos: ${dbName}`
      });
    } catch (error) {
      res.status(500).json({ error: 'Error al conectar a MongoDB Atlas.' });
    }
  });

  var Shema;

// Ruta POST para crear esquema de la colección
app.post('/createShema/:collectionName', async (req, res) => {
    try {
        const collectionName = req.params.collectionName;
      // Obtén los datos del esquema del cuerpo de la solicitud
      const schemaData = req.body;
  
      // Crea el esquema de la base de datos con los datos recibidos
      const databaseSchema = new mongoose.Schema(schemaData);
  
      // Crea un modelo basado en el esquema
      Shema = mongoose.model(collectionName, databaseSchema);
  
      res.status(201).json({ message: 'Esquema de base de datos creado exitosamente' });
    } catch (error) {
      res.status(500).json({ error: 'Error al crear el esquema de la base de datos' });
    }
  });


// Ruta POST para crear documento de acuerdo al schema creado
  app.post('/createDocument', async (req, res) => {
    try {
      // Crea una nueva instancia del Schema(collection) y recibe los datos del body de acuerdo al schema creado para guardar los datos del documento.
      const newDocument = new Shema(req.body);
  
      // Guarda el documento 
      await newDocument.save();
  
      res.status(201).json(newDocument);
    } catch (error) {
      res.status(500).json({ error: 'Error al crear la colección.' });
    }
  });
  


/* 

// Define el esquema de la base de datos
const databaseSchema = new mongoose.Schema({
  name: String,
});

// Crea un modelo basado en el esquema
const Database = mongoose.model('Database', databaseSchema);



// Ruta POST para crear bases de datos
 app.post('/databases', async (req, res) => {
  try {
    // Crea una nueva instancia de la base de datos
    const newDatabase = new Database({
      name: req.body.name,
    });

    // Guarda la nueva base de datos en MongoDB
    await newDatabase.save();

    res.status(201).json(newDatabase);
  } catch (error) {
    res.status(500).json({ error: 'Error al crear la base de datos' });
  }
});
 
 */

/* // Ruta POST para crear bases de datos y colecciones
app.post('/databases/:databaseName/collections/:collectionName', async (req, res) => {
    try {
      const databaseName = req.params.databaseName;
      const collectionName = req.params.collectionName;
  
      // Obtiene una instancia del objeto de base de datos
      const db = mongoose.connection.db;
  
      // Crea una base de datos si no existe
      await db.createDatabase(databaseName);
  
      // Selecciona la base de datos
      const database = db.db(databaseName);
  
      // Crea una colección si no existe
      await database.createCollection(collectionName);
  
      res.status(201).json({
        message: `Se ha creado la base de datos '${databaseName}' y la colección '${collectionName}'.`
      });
    } catch (error) {
      res.status(500).json({ error: 'Error al crear la base de datos y la colección.' });
    }
  });
 */

// Puerto en el que se ejecutará el servidor
const port = 3000;

// Inicia el servidor
app.listen(port, () => {
  console.log(`Servidor en funcionamiento en el puerto ${port}`);
});