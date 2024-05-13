const Sequelize = require('sequelize');
const Property = require('./property');
const env = process.env.NODE_ENV || 'development';
const config = require('../config/config')[env];

const db = {};
const sequelize = new Sequelize(config.database, config.username, config.password, {
    host: config.host,
    dialect: config.dialect
});

Property.initiate(sequelize);

db.sequelize = sequelize;
db.Property = Property;

module.exports = db;