const express = require('express');
const router = express.Router();
const { renderMain } = require('../controllers/page');

router.get('/', renderMain);

module.exports = router;