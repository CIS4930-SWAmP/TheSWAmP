/*
NOTE:
Requires NPM, Node, and Electron to run front end tests.
 */

const Nightmare = require('nightmare')
const assert = require('assert')

var r1 = Math.round(Math.random()*100000);
var r2 = Math.round(Math.random()*100000);

var randprice = Math.round(Math.random()*100);
var randdesc = "test description "+Math.round(Math.random()*1000);

describe('Public Pages', function() {
    // Recommended: 5s locally, 10s to remote server, 30s from airplane ¯\_(ツ)_/¯
    this.timeout('18s')

    let nightmare = null
    beforeEach(() => {
        // nightmare = new Nightmare()
        nightmare = new Nightmare({show: true}) //for showing the browser
    })

    describe('Load Tile View', () => {
        it('should load without error', done => {
            // your actual testing urls will likely be `http://localhost:port/path`
            nightmare.goto('https://the-swamp.herokuapp.com/html/eventTile.html')
                .wait(1000)
                .end()
                .then(function (result) {
                    done()
                })
                .catch(done)
        })
    })

    describe('Load List View', () => {
        it('should load without error', done => {
            // Should load list view without issue
            nightmare.goto('https://the-swamp.herokuapp.com/html/eventList.html')
                .wait(1000)
                .end()
                .then(function (result) {
                    done()
                })
                .catch(done)
        })
    })

    describe('/users/createUser', () => {
        it('should work register a new user then go to homepage', done => {
            nightmare
                .goto('https://the-swamp.herokuapp.com/')
                .type('#username', '1234')
                .type('[name="password"]', '1234')
                .type('[type="text"]', 't'+r1)
                .type('[type="text"]', 't'+r1)
                .type('[type="phone"]', '111-111-1111')
                .type('[type="email"]', 't'+r2+'@ufl.edu')
                .click('#submitbtn')
                .wait(2000)
                .end()
                .then(function (result) { done() })
                .catch(done)
        })
    })

    describe('Fail login', () => {
        it('should fail to sign in due to mismatch with password and password confirmation', done => {
            nightmare
                .goto('https://the-swamp.herokuapp.com/')
                .type('#username', '1234')
                .type('[name="password"]', '12345')
                .click('#submitbtn')
                .wait(1000)
                .end()
                .then(function (result) { done() })
                .catch(done)
        })
    })

    describe('Pass Login', () => {
        it('should sign in to account 1234', done => {
            nightmare
                .goto('https://the-swamp.herokuapp.com/')
                .type('#username', '1234')
                .type('[name="password"]', '1234')
                .click('#submitbtn')
                .wait(1000)
                .end()
                .then(function (result) { done() })
                .catch(done)
        })
    })
})

