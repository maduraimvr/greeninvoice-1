/**
 * invoicedto
 */
CREATE TABLE `invoicedto` (
  `invoiceNumber` varchar(25) NOT NULL,
  `invoiceDate` date DEFAULT NULL,
  `customerDetails` tinyblob,
  `totalAmount` float DEFAULT NULL,
  PRIMARY KEY (`invoiceNumber`),
  UNIQUE KEY `invoiceNumber_UNIQUE` (`invoiceNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/**
 * for joining two tables
 */
CREATE TABLE `invoicedto_itemdetailpojodto` (
  `invoiceDto_invoiceNumber` varchar(255) NOT NULL,
  `itemdetailsList_itemId` bigint(20) NOT NULL,
  UNIQUE KEY `UK_6s12tqgwek7m26cd6wnckisj0` (`itemdetailsList_itemId`),
  UNIQUE KEY `UK_nl0l4gq3tmum1wcjerfxso821` (`itemdetailsList_itemId`),
  KEY `FK_429tewstjdgek89qqg8us30vs` (`invoiceDto_invoiceNumber`),
  CONSTRAINT `FK_429tewstjdgek89qqg8us30vs` FOREIGN KEY (`invoiceDto_invoiceNumber`) REFERENCES `invoicedto` (`invoiceNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



/**
 * list of itemdetails table
 */
CREATE TABLE `itemdetailpojodto` (
  `itemId` mediumint(25) NOT NULL AUTO_INCREMENT,
  `serialNumber` varchar(25) NOT NULL,
  `itemDescription` varchar(45) NOT NULL,
  `itemQuantity` int(11) NOT NULL,
  `itemRate` float NOT NULL,
  `amount` float NOT NULL,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/**
 * userdto table
 */
CREATE TABLE `greeninvoice`.`userdto` (
  `username` VARCHAR(10) NOT NULL,
  `firstName` VARCHAR(25) NOT NULL,
  `lastName` VARCHAR(25) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` BLOB NOT NULL,
  `isBlocked` TINYINT(4) NULL DEFAULT '0',
  `profileImage` VARCHAR(85) NULL DEFAULT 'default',
  PRIMARY KEY (`username`));






