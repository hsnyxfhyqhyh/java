create database bible
	CHARACTER SET utf8;

use bible;

create table books(
	book_id MEDIUMINT NOT NULL AUTO_INCREMENT,
	book_Number MEDIUMINT,
	book_Name varchar(100),
	book_Name_EN varchar(100),
	chapter_count MEDIUMINT,
	Testament char (3),
	PRIMARY KEY(book_id)
);

DROP TABLE books ;

create table verses(
	verse_id MEDIUMINT NOT NULL AUTO_INCREMENT,
	book_Number MEDIUMINT(100),
	book_chapter_index MEDIUMINT,
	verse_index MEDIUMINT,
	verse_hhb varchar(1000),
	verse_niv varchar(1000),
	
	PRIMARY KEY(verse_id)
);


DROP TABLE verses;;




INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('40', '马太福音', 'Matthew', '28', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('41', '马可福音', 'Mark', '16', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('42', '路加福音', 'Luke', '24', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('43', '约翰福音', 'John', '21', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('44', '使徒行传', 'Acts', '28', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('45', '罗马书', 'Romans', '16', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('46', '哥林多前书', 'Corinthians1', '16', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('47', '哥林多后书', 'Corinthians2', '13', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('48', '加拉太书', 'Galatians', '6', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('49', '以弗所书', 'Ephesians', '6', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('50', '腓力比书', 'Philippians', '4', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('51', '歌罗西书', 'Colossians', '4', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('52', '帖撒罗尼迦前书', 'Thessalonians1', '5', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('53', '帖撒罗尼迦后书', 'Thessalonians2', '3', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('54', '提摩太前书', 'Timothy1', '6', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('55', '提摩太后书', 'Timothy2', '4', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('56', '提多书', 'Titus', '3', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('57', '腓利门书', 'Philemon', '1', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('58', '希伯来书', 'Hebrews', '13', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('59', '雅各书', 'James', '5', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('60', '彼得前书', 'Peter1', '5', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('61', '彼得后书', 'Peter2', '3', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('62', '约翰一书', 'John1', '5', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('63', '约翰二书', 'John2', '1', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('64', '约翰三书', 'John3', '1', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('65', '犹大书', 'Jude', '1', 'NEW');
INSERT INTO `bible`.`books` (`book_Number`, `book_Name`, `book_Name_EN`, `chapter_count`, `Testament`) VALUES ('66', '启示录', 'Revelation', '22', 'NEW');
	

