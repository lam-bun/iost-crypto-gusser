var PlayerFactory = artifacts.require("./PlayerFactory.sol");
var PlayerHelper = artifacts.require("./PlayerHelper.sol");
var Battle = artifacts.require("./Battle.sol");

module.exports = function(deployer) {
  deployer.deploy(PlayerFactory);
  deployer.deploy(PlayerHelper);
  deployer.deploy(Battle);
};
