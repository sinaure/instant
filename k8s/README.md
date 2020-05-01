sudo nano /var/snap/microk8s/current/args/kube-apiserver
ADD --allow-privileged

HEADLESS

mykubectl apply -f es-data.yml
mykubectl apply -f es-client.yml
mykubectl apply -f es-master.yml
mykubectl apply -f es-hq.yml

NORMAL


