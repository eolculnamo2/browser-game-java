const path = require('path')

module.exports = {
    entry: './src/Index.js',
    output: {
        path: path.resolve('./assets/dist'),
        filename: 'bundle.js'
    },
    mode: "development",
    devServer: {
        contentBase: path.join(__dirname, 'public'),
        publicPath: '/',
        compress: true,
        port: 3000,
        proxy: {
            '/': 'http://127.0.0.1:8080/browser-game/'
          }
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