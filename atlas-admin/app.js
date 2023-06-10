const express = require('express');
const bodyParser = require('body-parser');
const mongodb = require('mongodb');

const app = express();
const port = 3000;

app.use(bodyParser.json());

// MongoDB connection setup
const mongoURL = 'mongodb://localhost:27018'; // Update with your MongoDB Atlas connection details

let db;

//Intenten crear un cluster y und bd ustedes para lo de la conexión de la IP, porque por el momento 
//Solo está recibiendo conexiones desde mi IP (hay que mirar cómo camiar eso)
mongodb.MongoClient.connect("mongodb+srv://camilac54:JZLdO4SeG87KK9bz@cluster0.ynckr7i.mongodb.net/?retryWrites=true&w=majority/prueba", { useNewUrlParser: true })
  .then((client) => {
    db = client.db('prueba'); // Replace 'your-database-name' with your actual database name
    console.log('Connected to MongoDB Atlas');
  })
  .catch((err) => {
    console.error('Failed to connect to MongoDB Atlas:', err);
    process.exit(1); // Terminate the Node.js process with a non-zero exit code
  });

// API endpoint for user creation
app.post('/api/users', (req, res) => {
  const { username, email, password } = req.body;

  // Insert the user into the 'users' collection
  db.collection('users').insertOne({ username, email, password })
    .then(() => {
      console.log('User created successfully');
      res.status(201).json({ message: 'User created successfully' });
    })
    .catch((err) => {
      console.error('Failed to create user:', err);
      res.status(500).json({ error: 'Failed to create user' });
    });
});

// Start the server
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
