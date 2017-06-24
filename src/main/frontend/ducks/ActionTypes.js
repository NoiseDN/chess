function createSymbols(entity) {
    return {
        REQUEST: Symbol(`Fetch ${entity} request`),
        SUCCESS: Symbol(`Fetch ${entity} success`),
        ERROR: Symbol(`Fetch ${entity} error`)
    };
}

module.exports = {
    FIELD : createSymbols('Field'),
    MOVES : createSymbols('Moves')
};