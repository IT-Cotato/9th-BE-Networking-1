const express = require('express');
const propertiesController = require('../controllers/propertiesController');
const router = express.Router();

router.get('/', propertiesController.getPropertiesByZipCode);
router.post('/', propertiesController.createProperty);
router.delete('/', propertiesController.deletePropertyByRoadNameAddress);

module.exports = router;