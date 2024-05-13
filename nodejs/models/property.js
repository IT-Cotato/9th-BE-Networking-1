const { Model, DataTypes } = require('sequelize');

class Property extends Model {
    static initiate(sequelize) {
        this.init({
            id: {
                type: DataTypes.BIGINT,
                autoIncrement: true,
                primaryKey: true
            },
            postalCode: {
                type: DataTypes.STRING(50),
                allowNull: false,
            },
            roadAddress: {
                type: DataTypes.STRING(100),
                allowNull: true,
            },
            jibunAddress: {
                type: DataTypes.STRING(100),
                allowNull: true,
            }
        }, {
            sequelize,
            modelName: 'Property',
            tableName: 'properties',
            timestamps: true,
            underscored: false,
            paranoid: true,
            charset: 'utf8',
            collate: 'utf8_general_ci',
        });
    }
}

module.exports = Property;