const { Property } = require('../models/index');

exports.getPropertiesByZipCode = async (zipCode) => {
    return await Property.findAll({
        where: {
            postalCode: zipCode
        }
    });
};