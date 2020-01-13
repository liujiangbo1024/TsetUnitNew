#!/usr/bin/env bash
#auto make install MySQL
#BY liujiangbo 2019-5-20
#Define variables PATH
#sh ./auto_mysql_install.sh mysql-5.6.20.tar.gz
# $0=auto_mysql_install.sh  $1=mysql-5.6.20.tar.gz
#-z str1当串的长度为0时为真(空串)
if [ -z "$1" ];then
	echo -e "\033[32mPlease Input Arg \$1 ,Usage {sh $0 mysql-5.6.20.tar.gz }\033[0m"
	exit 0
fi

echo -e "\033[32m----------------------------------------------\033[0m"
#输出mysql56
VERSION=`echo $1|awk -F'.' '{print $1,$2}'|sed 's/\-//g;s/ //g'`
#输出是mysql-5.6.20
SQL_DIR=`echo $1|sed 's#.tar.*##g;'`
PORT=3361
#实例路径 data etc log路径
MYPATH=/data/servers/mysql3361
#安装包解压位置
INSTALLPATH=/data/software/$SQL_DIR
DATE=`date +%m%d-%H%M`
IP=`ifconfig eth0|grep "Bcast"|awk '{print $2}'|cut -d: -f2`

#添加用户和创建data目录
groupadd mysql
useradd -g mysql mysql -s /usr/sbin/nologin
mkdir -p $MYPATH/{data,log,tmp}

#解压的包
tar xzf $1 ;cd $SQL_DIR


#配置文件my.cnf
cat >$MYPATH/my.cnf<<EOF

[client]
port            = $PORT
socket          = $MYPATH/tmp/mysql.sock
[mysqld]
port            = $PORT
socket          = $MYPATH/tmp/mysql.sock
datadir         = $MYPATH/data
read_only       = 0

#--- GLOBAL ---#
character-set-server    = utf8
lower_case_table_names  = 1
log-output              = FILE
log-error               = $MYPATH/log/error.log
#general_log
general_log_file        = $MYPATH/log/mysql.log
pid-file                = $MYPATH/data/mysql.pid
slow-query-log
slow_query_log_file     = $MYPATH/log/slow.log
tmpdir                  = $MYPATH/tmp/
long_query_time         = 0.1
sync_binlog             = 1
optimizer_switch = 'index_merge_intersection=off'

#--------------#

#thread_concurrency      = 16
thread_cache_size       = 512
table_open_cache        = 16384
table_definition_cache  = 16384
sort_buffer_size        = 2M
join_buffer_size        = 2M
read_buffer_size        = 4M
read_rnd_buffer_size    = 4M
key_buffer_size         = 64M
myisam_sort_buffer_size = 64M
tmp_table_size          = 256M
max_heap_table_size     = 256M
open_files_limit        = 65535
#query_cache_size       = 2G


#--- NETWORK ---#
back_log                = 1024
max_allowed_packet      = 16M
interactive_timeout     = 28800
wait_timeout            = 28800
skip-external-locking
max-connections         = 1000
max_user_connections    = 0
max_connect_errors	= 100
skip-name-resolve

#--- REPL ---#
server-id               = 1412361375
log-bin                 = mysql-bin
binlog_format           = ROW
expire_logs_days        = 7
relay-log               = relay-log
#replicate-ignore-db     = test
log_slave_updates
skip-slave-start


#--- INNODB ---#
default-storage-engine          = INNODB
innodb_data_home_dir            = $MYPATH/data
innodb_data_file_path=ibdata1:2G;ibdata2:2G:autoextend
innodb_autoextend_increment     = 500
innodb_log_group_home_dir       = $MYPATH/data
innodb_buffer_pool_size         = 4G
#innodb_additional_mem_pool_size = 128M
innodb_log_files_in_group=3
innodb_log_file_size=536870912
innodb_log_buffer_size          = 8M
innodb_flush_log_at_trx_commit  = 1
innodb_lock_wait_timeout        = 120
innodb_flush_method             = O_DIRECT
innodb_max_dirty_pages_pct      = 75
innodb_io_capacity              = 1000
innodb_thread_concurrency       = 32
innodb_open_files               = 65535
innodb_file_per_table           = 1

[mysqldump]
quick
max_allowed_packet = 16M

[mysql]
auto-rehash
# Remove the next comment character if you are not familiar with SQL
#safe-updates
default-character-set=utf8
prompt = "MySQL \u@[\d]>"
[myisamchk]
key_buffer_size = 128M
sort_buffer_size = 128M
read_buffer = 2M
write_buffer = 2M

[mysqlhotcopy]
interactive-timeout

EOF

#授权mysql用户
chown -R mysql:mysql $MYPATH
chown -R mysql:mysql $MYPATH/{data,log,tmp}

#初始化
echo "Now install $VERSION,Please wait......"
$INSTALLPATH/scripts/mysql_install_db --defaults-file=$MYPATH/my.cnf --user=mysql --basedir=$INSTALLPATH --datadir=$MYPATH/data

echo "Now start mysql....."
#启动mysql
$INSTALLPATH/bin/mysqld --defaults-file=$MYPATH/my.cnf --user=mysql --basedir=$INSTALLPATH --datadir=$MYPATH/data &



echo
echo -e "\033[35m----------------------------------------------\033[0m"
echo "Mysql Install success,Install PATH to /export/servers/mysqltest/$VERSION/"
echo -e "You can connections $VERSION:\n $SQL_DIR/bin/mysql -S $MYPATH/tmp/mysql.sock\n alter user user() identified by 'XXXXX';\n"


#-----------------------------------------------------------
###config Mysql My.cnf #######



cat <<EOF
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

++++++++++You can identified new user++++++++++++++++++++++

 grant all privileges on *.* to 'root'@'%' identified by '1qaz@WSX' with grant option;
flush privileges;

create user 'eclp'@'%'identified by 'eclp';
grant all privileges on *.* to 'eclp'@'%';
flush privileges;

++++++++++You can config Mysql Repliation+++++++++++++++++++
Master Server Exec Command Follows:
grant  replication  slave  on *.* to  'replication'@'%'  identified by  'jd.com';
flush privileges;


show master status;
grant all on *.* to root@'%' identified by "1qaz@WSX";

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Slave Server Exec Command Follows:
change master to master_host='$IP',master_user='replication',master_password='jd.com',master_log_file='mysql-bin.000001',master_log_pos=325;

EOF
