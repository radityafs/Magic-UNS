'use strict';

/**
 * asset-history service
 */

const { createCoreService } = require('@strapi/strapi').factories;

module.exports = createCoreService('api::asset-history.asset-history');
