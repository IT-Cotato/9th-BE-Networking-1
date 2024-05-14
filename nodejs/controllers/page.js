exports.renderMain = (req, res, next) => {
    res.render('main', {
        title: 'Real Estate Management Project',
    });
};