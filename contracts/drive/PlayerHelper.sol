pragma solidity 0.4.21;
import "./PlayerFactory.sol";


contract PlayerHelper is PlayerFactory {

    modifier onlyMyself(address _address) {
        require(msg.sender == _address);
        _;
    }
    
    function getUserInfo(address _address) public onlyMyself(_address) view returns(
        uint id,
        string name,
        uint32 level,
        uint32 battleCount,
        uint16 victoryCount,
        uint16 defeatCount
    ) {
        User memory user = addressToUser[_address];
        return (user.id, user.name, user.level, user.battleCount, user.victoryCount, user.defeatCount);
    }
}