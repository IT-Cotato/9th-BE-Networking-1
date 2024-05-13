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

exports.createProperty = async (req, res) => {
    const { zipCode, roadNameAddress, landLotNameAddress } = req.body;
    if (!zipCode || !roadNameAddress || !landLotNameAddress) {
        return res.status(400).json({ error: 'All fields are required' });
    }

    try {
        const property = await propertiesService.createProperty(zipCode, roadNameAddress, landLotNameAddress);
        res.json({ id: property.id });
    } catch (err) {
        res.status(500).json({ error: 'Internal server error' });
    }
};

exports.deletePropertyByRoadNameAddress = async (req, res) => {
    const { roadNameAddress } = req.query;
    if (!roadNameAddress) {
        return res.status(400).json({ error: 'road-name-address is required' });
    }

    try {
        await propertiesService.deletePropertyByRoadNameAddress(roadNameAddress);
        res.json({ message: 'Property deleted successfully' });
    } catch (err) {
        res.status(500).json({ error: 'Internal server error' });
    }
};