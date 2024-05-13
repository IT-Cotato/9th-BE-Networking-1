const { Property } = require('../models/index');

exports.getPropertiesByZipCode = async (zipCode) => {
    return await Property.findAll({
        where: {
            postalCode: zipCode
        }
    });
};

exports.createProperty = async (zipCode, roadNameAddress, landLotNameAddress) => {
    return await Property.create({
        postalCode: zipCode,
        roadNameAddress,
        landLotNameAddress
    });
};

exports.deletePropertyByRoadNameAddress = async (roadNameAddress) => {
    return await Property.destroy({
        where: {
            roadNameAddress
        }
    });
};