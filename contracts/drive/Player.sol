pragma solidity 0.4.21;


contract Player {

    event NewUser(User user);

    mapping (address => User) private addressToUser;
    
    struct User {
        uint id;
        string name;
        uint32 level;
        uint32 battleCount;
        uint16 victoryCount;
        uint16 defeatCount;
    }

    function getUserInfo(address _address) public view returns(
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

    function createUser(address _address, string _name) public {
        uint id = uint(keccak256(_address));
        _createUser(_address, id, _name);
    }

    function _createUser(address _address, uint _id, string _name) internal {
        User memory user = User(_id, _name, 0, 0, 0, 0);
        NewUser(user);
        addressToUser[_address] = user;
    }

}