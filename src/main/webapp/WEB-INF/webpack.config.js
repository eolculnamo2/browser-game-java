const path = require('path')

module.exports = {
    entry: './src/Index.js',
    output: {
        path: path.resolve('./assets/dist'),
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                include: path.resolve(__dirname,'src'),
                exclude: /(node_modules)/,
                loader: 'babel-loader',
                query: {
                    presets: ['env','react']
                }
            },
            {
                test: /\.scss$/,
                loader: 'style-loader!css-loader!sass-loader'
            }
        ]
    }
}