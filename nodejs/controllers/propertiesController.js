const propertiesService = require('../services/propertiesService');

exports.getPropertiesByZipCode = async (req, res) => {
    const { zipCode } = req.query;
    if (!zipCode) {
        return res.status(400).json({ error: 'zip-code is required' });
    }

    try {
        const properties = await propertiesService.getPropertiesByZipCode(zipCode);
        res.json(properties);
    } catch (err) {
        res.status(500).json({ error: 'Internal server error' });
    }
};